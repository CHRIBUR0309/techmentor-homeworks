import React, { useEffect, useRef, useState } from 'react';
import RadioButtons from './RadioButtons';
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

  const editingTemplate = (
    <form className="" onSubmit={handleSubmit}>
      <div className="">
        <label className="" htmlFor={`${todoId}_${title}`}>
          タイトル
        </label>
        <input
          id={`${todoId}_${title}`}
          className=""
          type="text"
          value={newTitle}
          onChange={handleChange}
          ref={editFieldRef}
        />
        <fieldset className="">
          <legend className="">ステータス</legend>
          <div className="">
            <RadioButtons
              todoId={todoId}
              status={status}
              newStatus={newStatus}
              handleChange={handleChange}
              editFieldRef={editFieldRef}
            />
          </div>
        </fieldset>
        <label className="" htmlFor={`${todoId}_${details}`}>
          詳細
        </label>
        <input
          id={`${todoId}_${details}`}
          className=""
          type="textarea"
          value={newDetails}
          onChange={handleChange}
          ref={editFieldRef}
        />
      </div>
      <div className="">
        <button
          type="button"
          className=""
          onClick={() => {
            setEditing(false);
          }}
        >
          キャンセル
          <span className="">タイトル変更 {title}</span>
        </button>
        <button type="submit" className="">
          保存
          <span className="">タイトル {title}</span>
        </button>
      </div>
    </form>
  );
  const viewTemplate = (
    <>
      <div className="">
        <div className="">{title}</div>
        <RadioButtons
          todoId={todoId}
          status={status}
          newStatus={newStatus}
          handleChange={handleChange}
          editFieldRef={editFieldRef}
        />
      </div>
      <div className="">
        <button
          type="button"
          className=""
          onClick={() => {
            setEditing(true);
          }}
          ref={editButtonRef}
        >
          編集 <span className="">{title}</span>
        </button>
        <button
          type="button"
          className=""
          onClick={() => {
            deleteTodoItem(todoId);
          }}
        >
          削除 <span className="">{title}</span>
        </button>
      </div>
    </>
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
