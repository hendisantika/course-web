import CourseList from "@/container/course-list";

export default async function Page() {
    const res = await fetch(`${process.env.API_HOST}/api/courses`)

    if (!res.ok) {
        return 'Internal server error'
    }

    const data = await res.json()
    return (
        <CourseList 
            initialCourses={data}
        />
    );
}
