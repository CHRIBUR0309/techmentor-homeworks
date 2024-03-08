import React from 'react';
import ReactDOM from 'react-dom/client';
import 'the-new-css-reset/css/reset.css';
import App from './App';
import { type TodoItem } from '../public/types/Types';
import './index.css';

const todoItems: TodoItem[] = [];
const rootElement = document.getElementById('root');
if (!rootElement) {
  throw new Error('Failed to find the root element.');
}
const root = ReactDOM.createRoot(rootElement);
root.render(
  <React.StrictMode>
    <App propTodoItems={todoItems} />
  </React.StrictMode>
);
