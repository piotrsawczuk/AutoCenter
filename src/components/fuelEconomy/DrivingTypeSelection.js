import React from 'react';
import { Dropdown } from 'semantic-ui-react';

const fillOptionList = (drivingTypes) => {
  return drivingTypes.map(drivingType => {
    return {
        key : drivingType.id,
        value : drivingType.value,
        text : drivingType.drivingType
      }
  });
}

const DrivingTypeSelection = ({drivingTypes, isDisabled, onChange}) => (
  <Dropdown placeholder = 'Select driving type' 
  fluid search selection options = {fillOptionList(drivingTypes)}
  disabled = {isDisabled}
  onChange = {onChange} />
);

export default DrivingTypeSelection;