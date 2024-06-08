import {useState} from 'react';

import getConfig from 'next/config';

const {publicRuntimeConfig} = getConfig();
const {API_HOST} = publicRuntimeConfig;

const Index = ({initialCourses}) => {
    const [courses, setCourses] = useState(initialCourses);

}
