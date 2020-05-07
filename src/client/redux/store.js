import { createStore } from 'redux';
import GlobalState from './reducers';

const initalState = {
	pong: false,
	loggedUser: { isAdmin: true, name: 'Usuario', userId: 1 },
	lockers: [],
	users: [],
	rentals: [],
	locations: [],
	payments: [],
	lockerStates: [],
	rentalStates: [],
	paymentMethods: [],
};

const store = createStore(GlobalState, initalState);

export default store;
