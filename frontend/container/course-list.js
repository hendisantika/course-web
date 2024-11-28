"use client"
import Link from "next/link"
import {
  useCourseList,
} from "@/hook/course"

const CourseList = ({
  initialCourses,
}) => {
  const { courses } = useCourseList(initialCourses)

  const courseList = courses.map(course => {
    return <tr key={course.id}>
        <td style={{whiteSpace: 'nowrap'}}>{course.title}</td>
        <td>{course.categoryTitle}</td>
        <td>{course.duration}</td>
    </tr>
  });

  return (
    <div>
        <h1>Courses</h1>
        <Link href="/course">
          <button>
              New Course
          </button>
        </Link>

        <table>
          <thead>
          <tr>
              <th width="40%">Title</th>
              <th width="30%">Category</th>
              <th width="30%">Duration (minutes)</th>
          </tr>
          </thead>
          <tbody>
          {courseList}
          </tbody>
        </table>
    </div>
  )
}

export default CourseList