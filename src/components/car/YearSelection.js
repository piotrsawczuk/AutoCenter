import React from 'react';
import { Dropdown } from 'semantic-ui-react';

function fillOptionList(minYear, maxYear) {
  const years = [];
  for (let i = maxYear; i >= minYear; i--) {
    years.push(
      {
        key: i,
        value: i,
        text: i
      }
    );
  }
  return years;
}

const YearSelection = ({minYear, maxYear, onChange}) => (
  <Dropdown placeholder = 'Select year' 
  fluid search selection options = {fillOptionList(minYear, maxYear)}
  onChange = {onChange} />
);

export default YearSelection;