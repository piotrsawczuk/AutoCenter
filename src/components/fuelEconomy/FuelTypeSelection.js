import React from 'react';
import { Dropdown } from 'semantic-ui-react';

const fillOptionList = (fuelTypes) => {
  return fuelTypes.map(fuelType => {
    return {
        key: fuelType.id,
        value: fuelType.value,
        text: fuelType.fuelType
      }
  });
}

const FuelTypeSelection = ({fuelTypes, isDisabled, onChange}) => (
  <Dropdown placeholder = 'Select fuel type' 
  fluid search selection options = {fillOptionList(fuelTypes)}
  disabled = {isDisabled}
  onChange = {onChange} />
);

export default FuelTypeSelection;