import React, { useState } from 'react';
import { type Status } from '../../public/types/Types';
import '../index.css';
import AbstractButton from './AbstractButton';
import InputStatusForm from './InputStatusForm';
import InputTextForm from './InputTextForm';

const Form: React.FC<{
  addTodoItem: (
    title: string,
    status: Status,
    details: string
  ) => Promise<void>;
}> = ({ addTodoItem }) => {
  const [title, setTitle] = useState('');
  const [status, setStatus] = useState<Status>('Unprocessed');
  const [details, setDetails] = useState('');

  const handleSubmit = async (
    event: React.FormEvent<HTMLFormElement>
  ): Promise<void> => {
    event.preventDefault();
    await addTodoItem(title, status, details);
    setTitle('');
    setStatus('Unprocessed');
    setDetails('');
  };

  const handleChangeTitle = (
    event: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setTitle(event.target.value);
  };

  const handleChangeStatus = (
    event: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setStatus(event.target.value as Status);
  };

  const handleChangeDetails = (
    event: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setDetails(event.target.value);
  };

  return (
    <form className="" method="post" onSubmit={() => handleSubmit}>
      <InputTextForm
        isTitle={true}
        htmlFor="new-todo-title"
        id="new-todo-title"
        value={title}
        onChange={handleChangeTitle}
      />
      <InputStatusForm
        todoId="new-todo-status"
        status={status}
        newStatus={status}
        handleChange={handleChangeStatus}
      />
      <InputTextForm
        isTitle={false}
        htmlFor="new-todo-details"
        id="new-todo-details"
        value={details}
        onChange={handleChangeDetails}
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
