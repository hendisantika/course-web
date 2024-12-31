import {v4 as uuidv4} from "uuid"
import CourseCreate from "@/container/course-create"

export default async function Page() {
  const res = await fetch(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/courses/category`)

  if (!res.ok) {
    return 'Internal server error'
  }

  const data = await res.json()

  return (
    <CourseCreate 
      categories={data}
      userId={uuidv4()}
    />
  )
}
