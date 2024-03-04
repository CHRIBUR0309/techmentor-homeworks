import React from 'react';
import ReactDOM from 'react-dom/client';
import 'the-new-css-reset/css/reset.css';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { TodoItem } from '../public/types/Types';

const todoItems: TodoItem[] = [];
const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);
root.render(
  <React.StrictMode>
    <App propTodoItems={todoItems} />
  </React.StrictMode>,
);
reportWebVitals(console.log);
