import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../asyncRequests';

import {
	setRental, setRentals,
	updateRental as updateRentalAction,
	removeRental as removeRentalAction,
} from '../../redux/actions/rentals';

import { RentalStates } from '../../../server/constants';
import store from '../../redux/store';

const { dispatch } = store;

export const getAllRentals = () => fetchGet('/api/v1/admin/rentals')
	.then((r) => r.json())
	.then((rentals) => dispatch(setRentals(rentals)));

export const createRental = (newRental) => fetchPost('/api/v1/admin/rental', newRental)
	.then((r) => r.json())
	.then((rental) => dispatch(setRental(rental)));

export const updateRental = (newRental, oldRental) => {
	const url = `/api/v1/admin/rental/${oldRental.id}`;
	return fetchPut(url, newRental)
		.then((r) => r.json())
		.then((r) => { console.log(oldRental); console.log(newRental); console.log(r); return r; })
		.then((rental) => dispatch(updateRentalAction(rental)));
};

export const removeRental = (rental) => {
	const url = `/api/v1/admin/rental/${rental.id}`;
	return fetchDelete(url, {})
		.then((r) => r.json())
		.then(() => dispatch(removeRentalAction(rental)));
};

export const acceptRequest = (rental) => {
	const url = `/api/v1/admin/rental/${rental.id}?action=setReserved`;
	return fetchPut(url, {})
		.then((r) => r.json())
		.then((updatedRental) => dispatch(updateRentalAction(updatedRental)));
};

export const renewRental = (rental) => {
	const url = `/api/v1/admin/rental/${rental.id}?action=renew`;
	return fetchPut(url, {})
		.then((r) => r.json())
		.then((updatedRental) => dispatch(updateRentalAction(updatedRental)));
};

export const acceptRenew = (oldRental) => {
	const url = `/api/v1/admin/rental/${oldRental.id}`;
	return fetchPut(url, { rentalStateId: RentalStates.RESERVED })
		.then((r) => r.json())
		.then((rental) => dispatch(updateRentalAction(rental)));
};

export const claimRental = (oldRental) => {
	console.log(RentalStates);
	const body = {
		rentalStateId: RentalStates.CLAIMED,
	};
	console.log(body);
	const url = `/api/v1/admin/rental/${oldRental.id}`;
	return fetchPut(url, body)
		.then((r) => r.json())
		.then((rental) => dispatch(updateRentalAction(rental)));
};

export const endRental = (rental) => {
	const url = `/api/v1/admin/rental/${rental.id}?action=return`;
	return fetchPut(url, {})
		.then((r) => r.json())
		.then((updatedRental) => dispatch(updateRentalAction(updatedRental)));
};

export const requestRental = (rental) => {
	console.log(rental);
	fetchPost('/api/v1/admin/rental', rental);
};
