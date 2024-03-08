import React from 'react';
import { type StatusFilterKey } from '../../public/types/Types';
import '../index.css';

const FilterButton: React.FC<{
  statusFilter: StatusFilterKey;
  isPressed: boolean;
  setFilter: React.Dispatch<React.SetStateAction<StatusFilterKey>>;
}> = ({ statusFilter, isPressed, setFilter }) => {
  return (
    <button
      type="button"
      className=""
      aria-pressed={isPressed}
      onClick={() => {
        setFilter(statusFilter);
      }}
    >
      <span className="">表示 </span>
      <span>{statusFilter}</span>
      <span className=""> Todoリスト</span>
    </button>
  );
};

export default FilterButton;
