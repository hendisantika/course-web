import {useEffect, useState,} from "react";

export const useCourseList = (initialCourses) => {
  const [courses, setCourses] = useState(initialCourses);

  useEffect(() => {
      let eventSource = new EventSource(`/api/courses`);
    eventSource.onopen = (e) => {
        console.log('listen to api-sse endpoint', e)
    };

    eventSource.onmessage = (e) => {
        const course = JSON.parse(e.data);
        if (!courses.includes(course)) {
            setCourses(courses => [...courses, course]);
        }
    };

    eventSource.onerror = (e) => {
        console.log('error', e)
    };

    // returned function will be called on component unmount
    return () => {
        eventSource.close();
        eventSource = null;
    }
}, [])

  return {
    courses,
  }
}

export const useCourseCreate = (categories, userId) => {
  const emptyCourse = {
    title: null,
    description: null,
    categoryId: categories[0].id,
    createdByUserId: userId,
    duration: 60
  };

  const [course, setCourse] = useState(emptyCourse);
  const [saved, setSaved] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit  = async (e) => {
      e.preventDefault();
      await fetch('/api/courses', {
          method: 'POST',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({
              course: course,
          }),
      }).then(res => {
          if (res.status === 200) {
              setSaved(true);
          } else {
              setError('Error: ' + res.status + ' :: ' + res.statusText);
          }
      });
  }

  const handleChange = (e) => {
    e.preventDefault();

    const {name, value} = e.target;
    setCourse(prevState => ({
        ...prevState,
        [name]: value
    }));
  }

  return {
    course,
    saved,
    error,

    handleChange,
    handleSubmit,
  }
}
