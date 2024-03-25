'use client';

import {
  deleteItem,
  getAllItems,
  getItemsByStatus,
  insertItemWithDetails,
  insertItemWithoutDetails,
  updateItem
} from '@/services/api';
import { randomUUID } from 'crypto';
import React, { useEffect, useRef, useState } from 'react';
import {
  type Status,
  type StatusFilterKey,
  type TodoItem
} from '../public/types/Types';
import now from '../public/utilities/now';
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

const App: React.FC<unknown> = () => {
  const [filter, setFilter]: [
    filter: StatusFilterKey,
    setFilter: React.Dispatch<React.SetStateAction<StatusFilterKey>>
  ] = useState<StatusFilterKey>('All');
  const [filteredTodoItemList, setFilteredTodoItemList] = useState<
    React.JSX.Element[]
  >([]);
  const [todoItems, setTodoItems] = useState<TodoItem[]>([]);

  const addTodoItem = async (
    title: string,
    status: Status = 'Unprocessed',
    details: string = ''
  ): Promise<void> => {
    const todoId = randomUUID();
    const result =
      details === ''
        ? await insertItemWithoutDetails(todoId, title, status)
        : await insertItemWithDetails(todoId, title, status, details);
    if (result.status === 'FAILED') {
      console.log(result.message);
    }
  };

  const editTodoItem = async (
    todoId: string,
    newTitle: string,
    newStatus: Status,
    newDetails: string
  ): Promise<void> => {
    const result = await updateItem(todoId, newTitle, newStatus, newDetails);
    if (result.status === 'FAILED') {
      console.log(result.message);
    }
  };

  const deleteTodoItem = async (todoId: string): Promise<void> => {
    const result = await deleteItem(todoId);
    if (result.status === 'FAILED') {
      console.log(result.message);
    }
  };

  const filterList = FILTER_NAMES.map((statusFilter, i) => (
    <FilterButton
      key={`FILTER_NAMES[${i}]_${now()}`}
      statusFilter={statusFilter}
      isPressed={statusFilter === filter}
      setFilter={setFilter}
    />
  ));
  useEffect(() => {
    const f = async (): Promise<void> => {
      const FilteredTodoItems = async (): Promise<React.JSX.Element[]> => {
        const result = await getItemsByStatus(filter);
        if (result.status === 'SUCCEEDED') {
          return result.results.map((todoItem: TodoItem, i) => (
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
        } else {
          console.log(result.message);
          return [];
        }
      };
      setFilteredTodoItemList(await FilteredTodoItems());
    };
    void f();
  }, [filter]);
  const headingText =
    filteredTodoItemList.length === 0
      ? 'Todoリストは空です。'
      : `${filteredTodoItemList.length}個のTodoアイテムがあります。`;
  const listHeadingRef = useRef<HTMLHeadingElement>(null);
  useEffect(() => {
    const f = async (): Promise<void> => {
      const result = await getAllItems();
      setTodoItems(result.status === 'SUCCEEDED' ? result.results : []);
    };
    void f();
  }, []);
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
      <h2 tabIndex={-1} ref={listHeadingRef}>
        {headingText}
      </h2>
      <ul role="list" className="">
        {filteredTodoItemList}
      </ul>
    </div>
  );
};

export default App;
