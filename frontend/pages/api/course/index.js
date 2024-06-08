import nextConnect from 'next-connect';
import process from "next/dist/build/webpack/loaders/resolve-url-loader/lib/postcss";

const API_HOST = process.env.API_HOST;

const handler = nextConnect();

const sseMiddleware = (req, res, next) => {
    res.setHeader('Content-Type', 'text/event-stream');
    res.setHeader('Cache-Control', 'no-cache');
    res.flushHeaders();

    const flushData = (data) => {
        const sseFormattedResponse = `data: ${data}\n\n`;
        res.write("event: message\n");
        res.write(sseFormattedResponse);
        res.flush();
    }

    Object.assign(res, {
        flushData
    });

    next();
}

const stream = async (req, res) => {
    console.log("connect to sse stream");

    let eventSource = new EventSource(`${API_HOST}/course/sse`);
    eventSource.onopen = (e) => {
        console.log('listen to sse endpoint now', e)
    };
    eventSource.onmessage = (e) => {
        res.flushData(e.data);
    };
    eventSource.onerror = (e) => {
        console.log('error', e)
    };

    // close connection (detach subscriber)
    res.on('close', () => {
        console.log("close connection...");
        eventSource.close();
        eventSource = null;
        res.end();
    });
}
