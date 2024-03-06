import React from 'react';
import { useEffect, useRef, useState } from 'react';
import { Status } from '../../public/types/Types';

type StatusRadioButtonObject = {
  buttonLabel: string;
  value: Status;
};

const usePrevious = (value: boolean) => {
  const ref = useRef(false);
  useEffect(() => {
    ref.current = value;
  });
  return ref.current;
};

const Todo = ({
  todoId,
  title,
  status,
  details,
  key,
  changeTodoItemStatus,
  deleteTodoItem,
  editTodoItem,
}: {
  todoId: string;
  title: string;
  status: Status;
  details: string;
  key: string;
  changeTodoItemStatus: (todoId: string, changedStatus: Status) => void;
  deleteTodoItem: (todoId: string) => void;
  editTodoItem: (todoId: string, newTitle: string) => void;
}) => {
  const [isEditing, setEditing] = useState(false);
  const [newTitle, setNewTitle] = useState('');
  const [newStatus, setNewStatus] = useState<Status>('Unprocessed');
  const statusRadioButtons: StatusRadioButtonObject[] = [
    {
      buttonLabel: '未着手',
      value: 'Unprocessed',
    },
    {
      buttonLabel: '進行中',
      value: 'Proceeding',
    },
    {
      buttonLabel: '完了',
      value: 'Finished',
    },
  ];

  const RadioButtons = () => {
    return statusRadioButtons.map((radio) => {
      return (
        <>
          <input
            id={`${todoId}_${status}`}
            className=""
            type="radio"
            name="status"
            value={radio.value}
            checked={radio.value === newStatus}
            defaultChecked={radio.value === 'Unprocessed'}
            onChange={handleChange}
            ref={editFieldRef}
          />
          <label className="" htmlFor={`${todoId}_${status}`}>
            {radio.buttonLabel}
          </label>
        </>
      );
    });
  };

  const [newDetails, setNewDetails] = useState('');
  const editFieldRef = useRef<HTMLInputElement>(null);
  const editButtonRef = useRef<HTMLButtonElement>(null);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNewTitle(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
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
            <RadioButtons />
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
        <button type="button" className="" onClick={() => setEditing(false)}>
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
        <RadioButtons />
      </div>
      <div className="">
        <button
          type="button"
          className=""
          onClick={() => setEditing(true)}
          ref={editButtonRef}
        >
          編集 <span className="">{title}</span>
        </button>
        <button
          type="button"
          className=""
          onClick={() => deleteTodoItem(todoId)}
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
