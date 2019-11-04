import React from 'react';
import { Dropdown } from 'semantic-ui-react';

const fillOptionList = (trims) => {
  return trims.map(trim => {
    return {
        key: trim.model_id,
        value: trim.model_id,
        text: trim.model_trim
      }
  });
}

const TrimSelection = ({trims, isDisabled, onChange}) => (
  <Dropdown placeholder = 'Select trim' 
  fluid search selection options = {fillOptionList(trims)}
  disabled = {isDisabled}
  onChange = {onChange} />
);

export default TrimSelection;