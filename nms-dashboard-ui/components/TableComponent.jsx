import React from "react";
import store from "../stores/store.js";

class TableComponent extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        // Data
        var dataColumns = this.props.columns;
        var dataRows = this.props.data;

        var tableHeaders = (<thead>
            <tr key='tableheader'>
                {
                    dataColumns.map(function (column) {
                        return <th key={column}>{column}</th>;
                    }
                    )
                }
            </tr>
        </thead>);

        var tableBody = (
            <tbody>
                {
                    dataRows.map(function (row, index) {
                        return <tr key={index}>
                            {
                                dataColumns.map(function (column) {
                                    return <td key={column}>{row[column]}</td>;
                                })
                            }
                        </tr>
                    })
                }
            </tbody>);

        return (<table border="1">
            {tableHeaders}
            {tableBody}
        </table>);
    }
}

export default TableComponent;