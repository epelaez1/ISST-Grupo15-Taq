import { combineReducers } from 'redux';

function pong(state = false, action = {}) {
	switch (action.type) {
	case 'PONG':
		return true;
	default:
		return state;
	}
}

function loggedUser(state = {}, action = {}) {
	switch (action.type) {
	case 'MAKE_ADMIN':
		return { ...state, isAdmin: !state.isAdmin };
	default:
		return state;
	}
}

function lockers(state = [], action = {}) {
	switch (action.type) {
	case 'UPDATE_LOCKER':
		return state.map((locker) => (
			locker.id === action.payload.locker.id ? action.payload.locker : locker));
	case 'SET_LOCKER':
		return [...state, action.payload.locker];
	case 'SET_LOCKERS':
		return action.payload.lockers;
	case 'REMOVE_LOCKER':
		return state.filter((locker) => locker.id !== action.payload.locker.id);
	default:
		return state;
	}
}

function users(state = [], action = {}) {
	switch (action.type) {
	default:
		return state;
	}
}

function rentals(state = [], action = {}) {
	switch (action.type) {
	default:
		return state;
	}
}

function locations(state = [], action = {}) {
	switch (action.type) {
	case 'UPDATE_LOCATION':
		return state.map((location) => (
			location.id === action.payload.location.id ? action.payload.location : location));
	case 'SET_LOCATION':
		return [...state, action.payload.location];
	case 'SET_LOCATIONS':
		return action.payload.locations;
	case 'REMOVE_LOCATION':
		return state.filter((location) => location.id !== action.payload.location.id);
	default:
		return state;
	}
}

function payments(state = [], action = {}) {
	switch (action.type) {
	default:
		return state;
	}
}

function payment(state = [], action = {}) {
	switch (action.type) {
	default:
		return state;
	}
}

function lockerStates(state = [], action = {}) {
	switch (action.type) {
	default:
		return state;
	}
}

function rentalStates(state = [], action = {}) {
	switch (action.type) {
	default:
		return state;
	}
}

function paymentMethods(state = [], action = {}) {
	const { payload } = action;
	switch (action.type) {
	case 'UPDATE_PAYMENT_METHOD':
		return state.map((paymentMethod) => (
			paymentMethod.id === payload.paymentMethod.id ? payload.paymentMethod : paymentMethod));
	case 'SET_PAYMENT_METHOD':
		return [...state, payload.paymentMethod];
	case 'SET_PAYMENT_METHODS':
		return payload.paymentMethods;
	case 'REMOVE_PAYMENT_METHOD':
		return state.filter((paymentMethod) => paymentMethod.id !== payload.paymentMethod.id);
	default:
		return state;
	}
}

const GlobalState = (combineReducers({
	pong,
	loggedUser,
	lockers,
	users,
	rentals,
	locations,
	payments,
	payment,
	lockerStates,
	rentalStates,
	paymentMethods,
}));

export default GlobalState;
