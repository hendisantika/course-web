import {useState} from 'react';

import getConfig from 'next/config';

const {publicRuntimeConfig} = getConfig();
const {API_HOST} = publicRuntimeConfig;

const NewCourse = ({categories, userId}) => {
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

    const handleSubmit = async (e) => {
        e.preventDefault();
        await fetch('/api/course', {
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
}
