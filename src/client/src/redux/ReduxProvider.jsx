import { Provider } from 'react-redux';
import { createStore } from 'redux';
import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import GlobalState from './reducers';
import ErrorView from '../views/ErrorView';
import App from '../App';

// import { questions } from "../assets/mock-data";

export default class ReduxProvider extends React.Component {
	constructor(props) {
		super(props);
		this.initialState = {
			ping: false,
			loggedUser: {},
			lockers: [],
			users: [],
			rentals: [],
			locations: [],
			payments: [],
			payment: [],
			lockerStates: [],
			rentalStates: [],
			paymentMethods: [],
		};
		this.store = this.configureStore();
	}

	configureStore() {
		return createStore(GlobalState, this.initialState);
	}

	render() {
		return (
			<Provider store={this.store}>
				                <div style={{ height: '100%' }} >
                    <App store={ this.store}/>
                </div>
			</Provider>
		);
	}
}
