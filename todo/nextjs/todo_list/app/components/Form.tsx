'use client';

import React, { useState } from 'react';
import AbstractButton from './AbstractButton';
import InputStatusForm from './InputStatusForm';
import InputTextForm from './InputTextForm';
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
      <InputTextForm
        isTitle={true}
        htmlFor="new-todo-title"
        id="new-todo-title"
        value={title}
        onChange={handleChange}
      />
      <InputStatusForm
        todoId="new-todo-status"
        status={status}
        newStatus={status}
        handleChange={handleChange}
      />
      <InputTextForm
        isTitle={false}
        htmlFor="new-todo-details"
        id="new-todo-details"
        value={title}
        onChange={handleChange}
      />
      <AbstractButton
        buttonLabel="追加"
        buttonKind="other"
        buttonType="submit"
      />
    </form>
  );
};

export default Form;
