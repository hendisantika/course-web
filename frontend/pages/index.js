import {useEffect, useState} from 'react';

import getConfig from 'next/config';
import Link from "next/link";
import Head from "next/head";

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

    return (
        <div>
            <Head>
                <title>Course Service Example</title>
                <link rel="icon" href="/favicon.ico"/>
            </Head>

            <div>
                <h1>Courses</h1>
                <Link href="/course">
                    <button>
                        <a>New Course</a>
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
        </div>
    )
}

export const getServerSideProps = async () => {
    const res = await fetch(`${API_HOST}/course`);
    const data = await res.json();
    return {
        props: {
            initialCourses: data
        }
    }
};

export default Index;
