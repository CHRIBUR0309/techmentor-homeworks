/* eslint-disable @typescript-eslint/explicit-function-return-type */
import React from 'react';
import App from './App';
import { type TodoItem } from '../public/types/Types';

const Home = () => {
  const todoItems: TodoItem[] = [];
  return <App propTodoItems={todoItems} />;
};

export default Home;
