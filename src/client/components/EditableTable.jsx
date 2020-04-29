import React from 'react';
import { useSelector } from 'react-redux';
import MaterialTable from 'material-table';

export default function EditableTalbe(props) {
	const {
		model, title, columns,
		create, update, remove,
	} = props;
	const data = useSelector((state) => state[model]);

	return (
		<MaterialTable
			title={title}
			columns={columns}
			data={data}
			editable={{
				onRowAdd: (newData) => create(newData),
				onRowUpdate: (newData, oldData) => update(newData, oldData),
				onRowDelete: (oldData) => remove(oldData),
			}}
			options={{
				filtering: true,
				selection: true,
			}}
		/>
	);
}
