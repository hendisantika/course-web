import nextConnect from 'next-connect';
import process from "next/dist/build/webpack/loaders/resolve-url-loader/lib/postcss";

const API_HOST = process.env.API_HOST;

const handler = nextConnect();
