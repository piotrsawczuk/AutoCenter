import React from 'react';
import { Dropdown } from 'semantic-ui-react';

const fillOptionList = (models) => {
  return models.map(model => {
    return {
        key: model.model_name,
        value: model.model_name,
        text: model.model_name
      }
  });
}

const ModelSelection = ({models, isDisabled, onChange}) => (
  <Dropdown placeholder = 'Select model' 
  fluid search selection options = {fillOptionList(models)}
  disabled = {isDisabled}
  onChange = {onChange} />
);

export default ModelSelection;