import React from 'react';
import { useEffect, useRef, useState } from 'react';
import { Status, StatusFilterKey, TodoItem } from '../public/types/Types';
import FilterButton from './components/FilterButton';
import Form from './components/Form';
import Todo from './components/Todo';

const usePrevious = (value: number) => {
  const ref = useRef(0);
  useEffect(() => {
    ref.current = value;
  });
  return ref.current;
};

const FILTER_MAP: Record<StatusFilterKey, (todoItem: TodoItem) => boolean> = {
  All: (todoItem: TodoItem) => true,
  Unprocessed: (todoItem: TodoItem) => todoItem.status === 'Unprocessed',
  Proceeding: (todoItem: TodoItem) => todoItem.status === 'Proceeding',
  Finished: (todoItem: TodoItem) => todoItem.status === 'Finished',
};
const FILTER_NAMES = Object.keys(FILTER_MAP);

const App = ({ propTodoItems }: { propTodoItems: TodoItem[] }) => {
  const [todoItems, setTodoItems] = useState(propTodoItems);
  const [filter, setFilter]: [
    filter: StatusFilterKey,
    setFilter: React.Dispatch<React.SetStateAction<StatusFilterKey>>,
  ] = useState<StatusFilterKey>('All');

  const addTodoItem = (
    title: string,
    status: Status = 'Unprocessed',
    details: string = '',
  ) => {
    const newTodoItem: TodoItem = {
      todoId: `todo-${crypto.randomUUID()}`,
      title,
      status,
      details,
    };
    setTodoItems([...todoItems, newTodoItem]);
  };

  const editTodoItem = (todoId: string, newTitle: string) => {
    const editedTodoItemList = todoItems.map((todoItem: TodoItem) => {
      if (todoId === todoItem.todoId) {
        return { ...todoItem, title: newTitle };
      }
      return todoItem;
    });
    setTodoItems(editedTodoItemList);
  };

  const toggleTodoItemCompleted = (todoId: string) => {
    const updatedTodoItems = todoItems.map((todoItem: TodoItem) => {
      if (todoId === todoItem.todoId) {
        return { ...todoItem, status: !todoItem.status };
      }
      return todoItem;
    });
    setTodoItems(updatedTodoItems);
  };

  const deleteTodoItem = (todoId: string) => {
    const remainingTodoItems = todoItems.filter(
      (todoItem: TodoItem) => todoId !== todoItem.todoId,
    );
    setTodoItems(remainingTodoItems);
  };

  const filteredTodoItemList = todoItems
    .filter(FILTER_MAP[filter])
    .map((todoItem: TodoItem) => (
      <Todo
        todoId={todoItem.todoId}
        title={todoItem.title}
        status={todoItem.status}
        key={todoItem.todoId}
        toggleTodoItemCompleted={toggleTodoItemCompleted}
        deleteTodoItem={deleteTodoItem}
        editTodoItem={editTodoItem}
      />
    ));
  const filterList = FILTER_NAMES.map((title) => (
    <FilterButton
      key={title}
      title={title}
      isPressed={title === filter}
      setFilter={setFilter}
    />
  ));
  const headingText =
    filteredTodoItemList.length == 0
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
    <div className="">
      <h1>Todoリスト</h1>
      <Form addTodoItem={addTodoItem} />
      <div className="">{filterList}</div>
      <h2 id="list-heading" tabIndex={-1} ref={listHeadingRef}>
        {headingText}
      </h2>
      <ul role="list" className=""list-heading">
        {todoItemList}
      </ul>
    </div>
  );
};

export default App;
