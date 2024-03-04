import React from 'react';
import { StatusFilterKey } from '../../public/types/Types';

const FilterButton = ({
  key,
  title,
  isPressed,
  setFilter,
}: {
  key: string;
  title: string;
  isPressed: boolean;
  setFilter: React.Dispatch<React.SetStateAction<StatusFilterKey>>;
}) => {
  return (
    <button
      type="button"
      className=""
      aria-pressed={isPressed}
      onClick={() => setFilter(title)}
    >
      <span className="">表示 </span>
      <span>{title}</span>
      <span className=""> Todoリスト</span>
    </button>
  );
};

export default FilterButton;
