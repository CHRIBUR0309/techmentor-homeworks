import now from '../public/utilities/now';
import React, { useEffect, useRef, useState } from 'react';
import {
  type Status,
  type StatusFilterKey,
  type TodoItem
} from '../public/types/Types';
import FilterButton from './components/FilterButton';
import Form from './components/Form';
import Header from './components/Header';
import Todo from './components/Todo';
import './index.css';

const usePrevious = (value: number): number => {
  const ref = useRef(0);
  useEffect(() => {
    ref.current = value;
  });
  return ref.current;
};

const FILTER_MAP: Record<StatusFilterKey, (todoItem: TodoItem) => boolean> = {
  All: (_: TodoItem) => true,
  Unprocessed: (todoItem: TodoItem) => todoItem.status === 'Unprocessed',
  Proceeding: (todoItem: TodoItem) => todoItem.status === 'Proceeding',
  Finished: (todoItem: TodoItem) => todoItem.status === 'Finished'
};

const getKeys = <T extends Record<string, unknown>>(obj: T): Array<keyof T> => {
  return Object.keys(obj);
};

const FILTER_NAMES = getKeys(FILTER_MAP);

const App: React.FC<{ propTodoItems: TodoItem[] }> = ({ propTodoItems }) => {
  const [todoItems, setTodoItems] = useState(propTodoItems);
  const [filter, setFilter]: [
    filter: StatusFilterKey,
    setFilter: React.Dispatch<React.SetStateAction<StatusFilterKey>>
  ] = useState<StatusFilterKey>('All');

  const addTodoItem = (
    title: string,
    status: Status = 'Unprocessed',
    details: string = ''
  ): void => {
    const newTodoItem: TodoItem = {
      todoId: `todo-${crypto.randomUUID()}`,
      title,
      status,
      details
    };
    setTodoItems([...todoItems, newTodoItem]);
  };

  const editTodoItem = (todoId: string, newTitle: string): void => {
    const editedTodoItemList = todoItems.map((todoItem: TodoItem) => {
      if (todoId === todoItem.todoId) {
        return { ...todoItem, title: newTitle };
      }
      return todoItem;
    });
    setTodoItems(editedTodoItemList);
  };

  const deleteTodoItem = (todoId: string): void => {
    const remainingTodoItems = todoItems.filter(
      (todoItem: TodoItem) => todoId !== todoItem.todoId
    );
    setTodoItems(remainingTodoItems);
  };

  const filteredTodoItemList = todoItems
    .filter(FILTER_MAP[filter])
    .map((todoItem: TodoItem, i) => (
      <Todo
        key={`(todoItems.filter(FILTER_MAP[${filter}]))[${i}]_${now()}`}
        todoId={todoItem.todoId}
        title={todoItem.title}
        status={todoItem.status}
        details={todoItem.details}
        deleteTodoItem={deleteTodoItem}
        editTodoItem={editTodoItem}
      />
    ));
  const filterList = FILTER_NAMES.map((statusFilter, i) => (
    <FilterButton
      key={`FILTER_NAMES[${i}]_${now()}`}
      statusFilter={statusFilter}
      isPressed={statusFilter === filter}
      setFilter={setFilter}
    />
  ));
  const headingText =
    filteredTodoItemList.length === 0
      ? 'Todoリストは空です。'
      : `${filteredTodoItemList.length}個のTodoアイテムがあります。`;
  const listHeadingRef = useRef<HTMLHeadingElement>(null);
  const prevTodoItemLength = usePrevious(todoItems.length);
  useEffect(() => {
    if (todoItems.length < prevTodoItemLength) {
      listHeadingRef.current?.focus();
    }
  }, [todoItems.length, prevTodoItemLength]);
  return (
    <div className="mx-auto w-5/6 font-noto font-normal">
      <Header h1Text="Todoリスト" />
      <Form addTodoItem={addTodoItem} />
      <div className="">{filterList}</div>
      <h2 id="list-heading" tabIndex={-1} ref={listHeadingRef}>
        {headingText}
      </h2>
      <ul role="list" className="">
        {filteredTodoItemList}
      </ul>
    </div>
  );
};

export default App;
