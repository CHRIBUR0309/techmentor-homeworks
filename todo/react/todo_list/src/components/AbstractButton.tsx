import React from 'react';

const AbstractButton: React.FC<{
  buttonLabel: string | JSX.Element;
  buttonKind: 'delete' | 'filter' | 'other';
  buttonType: 'button' | 'submit';
  ariaPressed?: boolean;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
  ref?: React.LegacyRef<HTMLButtonElement>;
}> = ({ buttonLabel, buttonKind, buttonType, ariaPressed, onClick, ref }) => {
  const color = buttonKind === 'delete' ? 'red' : 'indigo';
  const className = `my-2 flex w-1/15 justify-center rounded-md px-3 py-1.5 leading-6 hover:bg-${color}-500 hover:text-white focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-${color}-600`;
  return (
    <div className="">
      {buttonKind === 'delete' ? (
        <button type="button" className={className} onClick={onClick}>
          {buttonLabel}
        </button>
      ) : buttonKind === 'filter' ? (
        <button
          type="button"
          className="my-2 flex w-1/6 justify-center rounded-md bg-indigo-600 px-3 py-1.5 leading-6 text-white hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
          aria-pressed={ariaPressed}
          onClick={onClick}
        >
          {buttonLabel}
        </button>
      ) : (
        <button
          type={buttonType}
          className={className}
          onClick={onClick}
          ref={ref}
        >
          {buttonLabel}
        </button>
      )}
    </div>
  );
};

export default AbstractButton;
