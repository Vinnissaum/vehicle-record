import styled from 'styled-components';

export default styled.input`
  width: 100%;
  height: 52px;
  color: ${({ theme }) => theme.colors.gray[200]};
  border-radius: 4px;
  border: none;
  background: ${({ theme }) => theme.colors.gray[700]};
  border: 2px solid #000;
  outline: none;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.04);
  transition: border-color 0.2s ease-in;
  padding: 0 16px;
  font-size: 16px;
  appearance: none;

  &:focus {
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    border-color: ${({ theme }) => theme.colors.primary.main};
  }

  &[disabled] {
    background-color: ${({ theme }) => theme.colors.gray[200]};
    border-color: transparent;
  }
`;
