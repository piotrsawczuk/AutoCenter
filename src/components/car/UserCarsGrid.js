import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import { Grid } from 'semantic-ui-react';
import PaginationComponent from '../PaginationComponent';
import UserCar from './UserCar';

class UserCarsGrid extends Component {
    render() {
        return (
            <div>
                <h2>My cars</h2>
                <Grid>
                    {this.props.userCars && this.props.userCars.content && this.props.userCars.content.map(userCar => (<UserCar key = {userCar.id} userCar = {userCar} />))}
                    <Grid.Row>
                        <Grid.Column width={16}>
                            <Link className="ui large primary left floated button" to="/cars/add">Add car</Link>
                            <div style = {{float:'right'}}>
                                {this.props.userCars && <PaginationComponent pagination = {this.props.userCars} onPageChange = {this.props.onPageChange}/>}
                            </div>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </div>
        );
    }
}

export default UserCarsGrid;