import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Button, Grid, Image } from 'semantic-ui-react';
import axios from 'axios';
import { deleteCar} from '../../actions/userCars';

class UserCar extends Component {
    state = { isDeleted: false }
    
    componentDidMount = () => {
        this.loadUserCarDetails(this.props.userCar.id)
    }

    loadUserCarDetails = (carId) => {
        const url = 'http://localhost:8080/cars';
        axios.get(`${url}/${carId}/details`, { headers: {
            'Authorization': localStorage.getItem('token')
        } }).then(res => {
            const userCarDetails = res.data;
            this.setState({ userCarDetails });
        });
    }

    deleteCar = () => {
        this.props.deleteCar(this.props.userCar.id);
        this.setState(
            { 
                isDeleted: true
            }
        );
    }

    render () {
        return (
            !this.state.isDeleted ?
                <Grid.Row columns={3}>
                    <Grid.Column width={4}>
                        {this.state.userCarDetails && this.state.userCarDetails.imageUrl ? 
                            <Image style={{borderRadius: '4px'}} className='ui medium image' src={this.state.userCarDetails.imageUrl}/> 
                        : 
                            <Image style={{borderRadius: '4px'}} className='ui medium image' src={require('../../assets/images/image.png')}/>}
                    </Grid.Column>
                    <Grid.Column width={10}>
                        <div style={{marginRight: '20%'}}>
                            <h3>{this.props.userCar.carName}</h3>
                            <div>
                                {this.state.userCarDetails && 'VIN: ' + this.state.userCarDetails.vin}
                                <br></br>
                                {this.state.userCarDetails && 'License plate: ' + this.state.userCarDetails.licencePlateNumber}
                                <br></br>
                                {this.state.userCarDetails && 'Color: ' + this.state.userCarDetails.color}
                            </div>
                        </div>
                    </Grid.Column>
                    <Grid.Column width={2}>
                        <Button.Group primary vertical={true} floated={'right'} size='mini'>
                            <Button>Show more info</Button>
                            <Button>Fuel economy</Button>
                            <Button>Repairs</Button>
                            <Button>Edit car</Button>
                            <Button onClick={this.deleteCar}>Delete car</Button>
                        </Button.Group>
                    </Grid.Column>
                </Grid.Row>
            :
                <div>
                    <Redirect to='/cars'/>
                </div>
        );
    }
    
};

const mapStateToProps = (state) => {
    return {
        userCars: state.userCarsReducer.userCars
    }
}

export default connect(mapStateToProps, { deleteCar }) (UserCar);