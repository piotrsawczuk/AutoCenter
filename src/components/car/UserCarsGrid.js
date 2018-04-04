import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import { Grid } from 'semantic-ui-react';
import PaginationComponent from '../PaginationComponent';
import UserCar from './UserCar';

class UserCarsGrid extends Component {

    render() {
        return (
            <Grid>
                {this.props.userCars && this.props.userCars.content && this.props.userCars.content.map(userCar => (<UserCar key = {userCar.id} userCar = {userCar} />))}
                <Grid.Row>
                    <Grid.Column width={16}>
                        <Link className="ui large primary left floated button" to="/addCar">Add car</Link>
                        <div style = {{float:'right'}}>
                            {this.props.userCars && this.props.userCars.totalPages ? <PaginationComponent pagination = {this.props.userCars} onPageChange = {this.props.onPageChange}/> : <div></div>}
                        </div>
                    </Grid.Column>
                </Grid.Row>

            </Grid>
        );
    }
}

export default  UserCarsGrid;