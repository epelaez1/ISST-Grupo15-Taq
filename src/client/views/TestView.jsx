import React, { Component } from 'react';
import { connect } from 'react-redux';

import { fetchGet } from '../util';
import { receivePong } from '../redux/actions/pong';

const doPing = function doPing(props) {
	fetchGet('/api/v1/ping')
		.then((r) => r.json())
		.then((r) => console.log(r))
		.then(() => props.dispatch(receivePong()));
};

class TestView extends Component {
	componentDidMount() {
		doPing(this.props);
	}

	componentDidUpdate() {
		doPing(this.props);
	}

	render() {
		const { ping } = this.props;
		return (
			<div>
				<h1>Hello, world!</h1>
				{!ping ? 'Awaiting pong...' : 'Pong!'}
			</div>
		);
	}
}

function mapStateToProps(state) {
	return {
		...state,
	};
}

export default connect(mapStateToProps)(TestView);
