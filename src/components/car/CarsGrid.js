import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import { Grid } from 'semantic-ui-react';
import PaginationComponent from '../PaginationComponent';
import Car from './Car';

class CarsGrid extends Component {
    
    render() {
        return (
            <div>
                <h2>My cars</h2>
                <Grid>
                    {this.props.cars && this.props.cars.content && this.props.cars.content.map(car => (<Car key = {car.id} car = {car} page = {this.props.cars.number}/>))}
                    <Grid.Row>
                        <Grid.Column width={16}>
                            <Link className="ui large primary left floated button" to="/cars/add">Add car</Link>
                            <div style = {{float:'right'}}>
                                {this.props.cars && <PaginationComponent pagination = {this.props.cars} onPageChange = {this.props.onPageChange}/>}
                            </div>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </div>
        );
    }
}

export default CarsGrid;