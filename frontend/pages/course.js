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

}
