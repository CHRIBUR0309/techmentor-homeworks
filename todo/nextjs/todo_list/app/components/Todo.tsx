import React, { useEffect, useRef, useState } from 'react';
import { type Status } from '../../public/types/Types';
import '../index.css';
import AbstractButton from './AbstractButton';
import InputStatusForm from './InputStatusForm';
import InputTextForm from './InputTextForm';

const usePrevious = (value: boolean): boolean => {
  const ref = useRef(false);
  useEffect(() => {
    ref.current = value;
  });
  return ref.current;
};

const Todo: React.FC<{
  todoId: string;
  title: string;
  status: Status;
  details: string;
  deleteTodoItem: (todoId: string) => Promise<void>;
  editTodoItem: (
    todoId: string,
    newTitle: string,
    newStatus: Status,
    newDetails: string
  ) => Promise<void>;
}> = ({ todoId, title, status, details, deleteTodoItem, editTodoItem }) => {
  const [isEditing, setEditing] = useState(false);
  const [newTitle, setNewTitle] = useState('');
  const [newStatus, setNewStatus] = useState<Status>('Unprocessed');
  const [newDetails, setNewDetails] = useState('');
  const editTitleFieldRef = useRef<HTMLInputElement>(null);
  const editStatusFieldRef = useRef<HTMLInputElement>(null);
  const editDetailsFieldRef = useRef<HTMLInputElement>(null);
  const editButtonRef = useRef<HTMLButtonElement>(null);

  const handleSubmit = async (
    event: React.FormEvent<HTMLFormElement>
  ): Promise<void> => {
    event.preventDefault();
    await editTodoItem(todoId, newTitle, newStatus, newDetails);
    setEditing(false);
    setNewTitle('');
    setNewStatus('Unprocessed');
    setNewDetails('');
  };

  const handleChangeTitle = (
    event: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setNewTitle(event.target.value);
  };

  const handleChangeStatus = (
    event: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setNewStatus(event.target.value as Status);
  };

  const handleChangeDetails = (
    event: React.ChangeEvent<HTMLInputElement>
  ): void => {
    setNewDetails(event.target.value);
  };

  const htmlForTitle = `${todoId}_${title}`;
  const htmlForDetails = `${todoId}_${details}`;
  const editingTemplate = (
    <form className="" method="post" onSubmit={() => handleSubmit}>
      <div className="">
        <InputTextForm
          isTitle={true}
          htmlFor={htmlForTitle}
          id={htmlForTitle}
          value={newTitle}
          onChange={handleChangeTitle}
          ref={editTitleFieldRef}
        />
        <InputStatusForm
          todoId={todoId}
          status={status}
          newStatus={newStatus}
          handleChange={handleChangeStatus}
          ref={editStatusFieldRef}
        />
        <InputTextForm
          isTitle={false}
          htmlFor={htmlForDetails}
          id={htmlForDetails}
          value={newDetails}
          onChange={handleChangeDetails}
          ref={editDetailsFieldRef}
        />
      </div>
      <div className="">
        <AbstractButton
          buttonLabel="キャンセル"
          buttonKind="other"
          buttonType="button"
          onClick={() => {
            setEditing(false);
          }}
        />
        <AbstractButton
          buttonLabel="保存"
          buttonKind="other"
          buttonType="submit"
        />
      </div>
    </form>
  );
  const viewTemplate = (
    <div className="my-2">
      <div className="">
        <div className="">{`ID：${todoId}`}</div>
        <div className="">{`タイトル：${title}`}</div>
        <div className="">{`ステータス：${status}`}</div>
        <div className="">{`詳細：${details}`}</div>
      </div>
      <div className="">
        <AbstractButton
          buttonLabel="編集"
          buttonKind="other"
          buttonType="button"
          onClick={() => {
            setEditing(true);
          }}
          ref={editButtonRef}
        />
        <AbstractButton
          buttonLabel="削除"
          buttonKind="delete"
          buttonType="button"
          onClick={() => {
            void deleteTodoItem(todoId);
          }}
        />
      </div>
    </div>
  );
  const wasEditing = usePrevious(isEditing);
  useEffect(() => {
    if (!wasEditing && isEditing) {
      editTitleFieldRef.current?.focus();
    } else if (wasEditing && !isEditing) {
      editButtonRef.current?.focus();
    }
  }, [wasEditing, isEditing]);
  return <li className="">{isEditing ? editingTemplate : viewTemplate}</li>;
};

export default Todo;
