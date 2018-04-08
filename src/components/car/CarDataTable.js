import React, { Component } from 'react';
import { Button, Table } from 'semantic-ui-react';

class CarDataTable extends Component {

    state = {
        showMoreData: false
    }

    onClickMoreData = () => {
        this.setState({ showMoreData: !this.state.showMoreData });
    }

    checkIfValueExists = (value) => {
        if (value)
            return value;
        else
            return '-';
    }

    calculatePStoHP = (value) => {
        if (value !== '-')
            return Math.round(value/1.0142777265087);
        else
            return '-';
    }

    render() {
        return (
            <div style={{ width: '100%', padding: '14px'}}>
                <Table color = {'blue'} key = {'blue'}>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell colSpan='2'>{`${this.props.trim.model_year} ${this.props.trim.make_display} ${this.props.trim.model_name} ${this.props.trim.model_trim}`}</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        <Table.Row>
                            <Table.Cell>Make</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.make_display)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Model</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_name)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Trim</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_trim)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Country of origin</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.make_country)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Year</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_year)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Body</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_body)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Engine fuel</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_fuel)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Engine displacement [cc]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_cc)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Transmission type</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_transmission_type)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Drive</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_drive)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>Top speed [km/h]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_top_speed_kph)}</Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>0-100 km/h [s]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_0_to_100_kph)}</Table.Cell>
                        </Table.Row>
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Number of seats</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_seats)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Number of doors</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_doors)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Weight [kg]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_weight_kg)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Length [mm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_length_mm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Width [mm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_width_mm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Height [mm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_height_mm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Wheelbase [mm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_wheelbase_mm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Fuel capacity [l]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_fuel_cap_l)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine position</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_position)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine type</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_type)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine cylinders</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_cyl)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine valves per cylinder</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_valves_per_cyl)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine power [PS | HP]</Table.Cell>
                            <Table.Cell textAlign='right'>{`${this.checkIfValueExists(this.props.trim.model_engine_power_ps)} | ${this.calculatePStoHP(this.checkIfValueExists(this.props.trim.model_engine_power_ps))}`}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine max power RPM</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_power_rpm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine torque [Nm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_torque_nm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine max torque RPM</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_torque_rpm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine bore [mm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_bore_mm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine stroke [mm]</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_stroke_mm)}</Table.Cell>
                        </Table.Row>}
                        {this.state.showMoreData && <Table.Row>
                            <Table.Cell>Engine compression ratio</Table.Cell>
                            <Table.Cell textAlign='right'>{this.checkIfValueExists(this.props.trim.model_engine_compression)}</Table.Cell>
                        </Table.Row>}
                    </Table.Body>
                </Table>
                <Button size={'small'} floated={'right'} primary onClick={() => this.onClickMoreData()}>{this.state.showMoreData ? 'Show less' : 'Show more'}</Button>
            </div>
        );
    }
}

export default CarDataTable;