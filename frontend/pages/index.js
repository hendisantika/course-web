import {useEffect, useState} from 'react';

import getConfig from 'next/config';

const {publicRuntimeConfig} = getConfig();
const {API_HOST} = publicRuntimeConfig;

const Index = ({initialCourses}) => {
    const [courses, setCourses] = useState(initialCourses);

    useEffect(() => {
        let eventSource = new EventSource(`/api/course`);
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

    const courseList = courses.map(course => {
        return <tr key={course.id}>
            <td style={{whiteSpace: 'nowrap'}}>{course.title}</td>
            <td>{course.categoryTitle}</td>
            <td>{course.duration}</td>
        </tr>
    });

}
