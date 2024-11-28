import {NextResponse} from 'next/server';
import EventSource from 'eventsource';

const API_HOST = process.env.API_HOST;

// GET handler for SSE stream
export async function GET(request) {
  const encoder = new TextEncoder();

  const stream = new ReadableStream({
      async start(controller) {
          const eventSource = new EventSource(`${API_HOST}/api/courses/sse`);

          eventSource.onopen = (e) => {
              console.log('Connected to SSE endpoint', e);
          };

          eventSource.onmessage = (e) => {
              const formattedData = `event: message\ndata: ${e.data}\n\n`;
              controller.enqueue(encoder.encode(formattedData));
          };

          eventSource.onerror = (e) => {
              console.error('SSE Error:', e);
              eventSource.close();
              controller.close();
          };

          // Handle client disconnect
          request.signal.addEventListener('abort', () => {
              console.log('Client disconnected');
              eventSource.close();
              controller.close();
          });
      }
  });

  return new NextResponse(stream, {
      headers: {
          'Content-Type': 'text/event-stream',
          'Cache-Control': 'no-cache',
          'Connection': 'keep-alive'
      }
  });
}

// POST handler for creating courses
export async function POST(request) {
  try {
      const course = await request.json();

      const response = await fetch(`${API_HOST}/api/courses`, {
          method: 'POST',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(course),
      });

      if (response.status === 201) {
          return NextResponse.json({ message: "ok" });
      }
      
      return NextResponse.json(
          { status: response.status, message: response.statusText },
          { status: response.status }
      );
  } catch (error) {
      console.error('Error creating course:', error);
      return NextResponse.json(
          { message: 'Internal Server Error' },
          { status: 500 }
      );
  }
}
