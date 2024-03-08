import React, { useState } from 'react';
import { type Status } from '../../public/types/Types';
import '../index.css';

const Form: React.FC<{
  addTodoItem: (title: string, status: Status, details: string) => void;
}> = ({ addTodoItem }) => {
  const [title, setTitle] = useState('');
  const [status, setStatus] = useState<Status>('Unprocessed');
  const [details, setDetails] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    addTodoItem(title, status, details);
    setTitle('');
    setStatus('Unprocessed');
    setDetails('');
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
    setTitle(event.target.value);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2 className="">
        <label htmlFor="new-todo-input" className="">
          新規のTodoアイテム
        </label>
      </h2>
      <input
        type="text"
        id="new-todo-input"
        className=""
        title="text"
        autoComplete="off"
        value={title}
        onChange={handleChange}
      />
      <button type="submit" className="">
        追加
      </button>
    </form>
  );
};

export default Form;
