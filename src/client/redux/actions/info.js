export function setLocationsLookup(locations) {
	return { type: 'SET_LOCATIONS_LOOKUP', payload: { locations } };
}
export function setPaymentMethodsLookup(paymentMethods) {
	return { type: 'SET_PAYMENT_METHODS_LOOKUP', payload: { paymentMethods } };
}
