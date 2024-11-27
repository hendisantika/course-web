"use client"
import { useCourseCreate } from "@/hook/course"
import Link from "next/link";

const CourseCreate = ({
  categories,
  userId,
}) => {
  const {
    course,
    error,
    saved,

    handleChange,
    handleSubmit,
  } = useCourseCreate(categories, userId)

  const categoryOptions = categories.map(c => {
    return <option key={c.id} value={c.id}>{c.title}</option>
  });

  return (
    <div>
      <h1>Create new course</h1>
      <Link href="/">
        <button>
            All Courses
        </button>
      </Link>

      {error && <h3>{error}</h3>}
      {saved && <h3>Congrats! The course '{course.title}' was saved successfully.</h3>}
      {!saved && (
          <form onSubmit={handleSubmit}>
            <label htmlFor="title">Course Title</label>
              <input type="text" id="title" name="title" placeholder="Course Title"
                      value={course.title || ''} onChange={handleChange}/>

              <label htmlFor="duration">Duration</label>
              <input type="text" id="duration" name="duration" placeholder="Course Duration in Minutes"
                      value={course.duration || ''} onChange={handleChange}/>

              <label htmlFor="category">Course Category</label>
              <select id="category" name="categoryId" value={course.categoryId || ''} onChange={handleChange}>
                  {categoryOptions}
              </select>

              <label htmlFor="description">Description</label>
              <textarea rows="10" id="description" name="description" placeholder="Description"
                        value={course.description || ''} onChange={handleChange}/>

              <input type="submit" value="Submit"/>
          </form>
        )
      }
    </div>
  )
}

export default CourseCreate