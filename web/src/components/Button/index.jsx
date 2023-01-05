import React from 'react';
import PropTypes from 'prop-types';
import { StyledButton } from './styles';

export default function Button({
  type, children, onClick,
}) {
  return (
    <StyledButton
      type={type}
      onClick={() => onClick()}
    >
      { children }
    </StyledButton>
  );
}

Button.propTypes = {
  children: PropTypes.node.isRequired,
  type: PropTypes.string,
  onClick: PropTypes.func,
};

Button.defaultProps = {
  type: 'button',
  onClick: () => {},
};
