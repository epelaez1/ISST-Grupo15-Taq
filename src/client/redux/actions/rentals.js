export function setRental(rental) {
	return { type: 'SET_RENTAL', payload: { rental } };
}
export function setRentals(rentals) {
	return { type: 'SET_RENTALS', payload: { rentals } };
}

export function removeRentals(rentals) {
	return { type: 'REMOVE_RENTALS', payload: { rentals } };
}

export function removeRental(rental) {
	return { type: 'REMOVE_RENTAL', payload: { rental } };
}

export function updateRentals(rentals) {
	return { type: 'UPDATE_RENTALS', payload: { rentals } };
}

export function updateRental(rental) {
	return { type: 'UPDATE_RENTAL', payload: { rental } };
}
