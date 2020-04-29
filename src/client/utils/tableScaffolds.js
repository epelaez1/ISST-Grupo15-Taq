import { LockerStates, RentalStates } from '../../server/constants';

// Common
const id = {
	field: 'id',
	title: 'ID',
	editable: 'never',
	width: 100,
};
const name = {
	field: 'name',
	title: 'Nombre',
	width: 200,
};

const description = {
	field: 'description',
	title: 'Descripción',
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};
const createdAt = {
	field: 'createdAt',
	title: 'Fecha de Creación',
	type: 'datetime',
	editable: 'never',
	filtering: false,
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};
const updatedAt = {
	field: 'updatedAt',
	title: 'Última modificación',
	type: 'datetime',
	editable: 'never',
	filtering: false,
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};

// Const
const lockerState = {
	field: 'lockerStateId',
	title: 'Estado',
	width: 200,
	lookup: {
		[LockerStates.UNAVAILABLE]: 'No Disponible',
		[LockerStates.AVAILABLE]: 'Disponible',
		[LockerStates.RESERVED]: 'Reservada',
		[LockerStates.RENTED]: 'Alquilada',
	},
};
const rentalState = {
	field: 'lockerStateId',
	title: 'Estado',
	width: 200,
	lookup: {
		[RentalStates.REQUESTED]: 'Solicitud enviada',
		[RentalStates.RESERVED]: 'Taquilla reservada',
		[RentalStates.RENTED]: 'Alquiler en curso',
		[RentalStates.CLAIMED]: 'Alquiler expirado, llaves pendientes de devolución',
		[RentalStates.RENEW_REQUESTED]: 'Renovación solicitada',
		[RentalStates.RETURNED]: 'Alquiler finalizado',

	},
};

// Rentals
const paymentMethod = {};

// Lockers
const lockerNumber = {
	field: 'lockerNumber',
	title: 'Nº',
	type: 'numeric',
	width: 100,
};
const location = {
	field: 'locationId',
	title: 'Localización',
	width: 200,
	lookup: {},
};

const allColumns = {
	locations: [id, name, description, createdAt, updatedAt],
	paymentMethods: [id, name, description, createdAt, updatedAt],
	lockers: [lockerNumber, location, lockerState, createdAt, updatedAt],
};

export const getLockersScaffold = (locations) => {
	const lookup = {};
	for (let i = 0; i < locations.length; i++) {
		lookup[locations[i].id] = locations[i].name;
	}
	const columns = [lockerNumber, { ...location, lookup }, lockerState, createdAt, updatedAt];
	return { id: 'id', title: 'Taquillas', columns };
};

export const scaffolds = {
	locations: {
		id: 'id',
		title: 'Localizaciones',
		columns: allColumns.locations,
	},
	paymentMethods: {
		id: 'id',
		title: 'Métodos de pago',
		columns: allColumns.paymentMethods,
	},
	lockers: {
		id: 'id',
		title: 'Taquillas',
		columns: allColumns.lockers,
	},
};
