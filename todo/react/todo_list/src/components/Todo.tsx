import React, { useEffect, useRef, useState } from 'react';
import AbstractButton from './AbstractButton';
import InputStatusForm from './InputStatusForm';
import InputTextForm from './InputTextForm';
import { type Status } from '../../public/types/Types';
import '../index.css';

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
  deleteTodoItem: (todoId: string) => void;
  editTodoItem: (todoId: string, newTitle: string) => void;
}> = ({ todoId, title, status, details, deleteTodoItem, editTodoItem }) => {
  const [isEditing, setEditing] = useState(false);
  const [newTitle, setNewTitle] = useState('');
  const newStatus = useState<Status>('Unprocessed')[0];

  const newDetails = useState('')[0];
  const editFieldRef = useRef<HTMLInputElement>(null);
  const editButtonRef = useRef<HTMLButtonElement>(null);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
    setNewTitle(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>): void => {
    event.preventDefault();
    editTodoItem(todoId, newTitle);
    setNewTitle('');
    setEditing(false);
  };

  const htmlForTitle = `${todoId}_${title}`;
  const htmlForDetails = `${todoId}_${details}`;
  const editingTemplate = (
    <form className="" onSubmit={handleSubmit}>
      <div className="">
        <InputTextForm
          isTitle={true}
          htmlFor={htmlForTitle}
          id={htmlForTitle}
          value={newTitle}
          onChange={handleChange}
          ref={editFieldRef}
        />
        <InputStatusForm
          todoId={todoId}
          status={status}
          newStatus={newStatus}
          handleChange={handleChange}
          ref={editFieldRef}
        />
        <InputTextForm
          isTitle={false}
          htmlFor={htmlForDetails}
          id={htmlForDetails}
          value={newDetails}
          onChange={handleChange}
          ref={editFieldRef}
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
            deleteTodoItem(todoId);
          }}
        />
      </div>
    </div>
  );
  const wasEditing = usePrevious(isEditing);
  useEffect(() => {
    if (!wasEditing && isEditing) {
      editFieldRef.current?.focus();
    } else if (wasEditing && !isEditing) {
      editButtonRef.current?.focus();
    }
  }, [wasEditing, isEditing]);
  return <li className="">{isEditing ? editingTemplate : viewTemplate}</li>;
};

export default Todo;
