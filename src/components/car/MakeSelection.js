import React from 'react';
import { Dropdown } from 'semantic-ui-react';

const fillOptionList = (makes) => {
  return makes.map(make => {
    return {
        key: make.make_id,
        value: make.make_id,
        text: make.make_display
      }
  });
}

const MakeSelection = ({makes, isDisabled, onChange}) => (
  <Dropdown placeholder = 'Select make' 
  fluid search selection options = {fillOptionList(makes)}
  disabled = {isDisabled} 
  onChange = {onChange} />
);

export default MakeSelection;