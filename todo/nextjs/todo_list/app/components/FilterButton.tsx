/* eslint-disable no-irregular-whitespace */
'use client';

import React from 'react';
import AbstractButton from './AbstractButton';
import { type StatusFilterKey } from '../../public/types/Types';
import '../index.css';

const FilterButton: React.FC<{
  statusFilter: StatusFilterKey;
  isPressed: boolean;
  setFilter: React.Dispatch<React.SetStateAction<StatusFilterKey>>;
}> = ({ statusFilter, isPressed, setFilter }) => {
  return (
    <>
      <AbstractButton
        buttonLabel={
          <div className="">
            <span>{`${statusFilter}　表示`}</span>
          </div>
        }
        buttonKind="filter"
        buttonType="button"
        ariaPressed={isPressed}
        onClick={() => {
          setFilter(statusFilter);
        }}
      />
    </>
  );
};

export default FilterButton;
