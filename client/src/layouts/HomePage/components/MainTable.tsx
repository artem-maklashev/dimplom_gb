function MainTable() {
    return (
        <div className="table-responsive">
            <div className="container mt-5">
                <div className="card">
                    <div className="card-header">
                        <h1>текущие данные</h1>
                    </div>
                    <table className="table table-fluide table-striped table-hover table-bordered">
                        {/* <span className="border border-warning"> */}
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Вид гипсокартона</th>
                                <th scope="col">План</th>
                                <th scope="col">Факт</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Декоратор ГСП А-УК 12,5-1200-2500</td>
                                <td>13300</td>
                                <td>2500</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Декоратор ГСП А-УК 12,5-1200-3000</td>
                                <td>5000</td>
                                <td>0</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>РБМ А-ПЛУК 9,5-1200-2500</td>
                                <td>0</td>
                                <td>5000</td>
                            </tr>
                        </tbody>
                        {/* </span> */}
                    </table>
                </div>
            </div>
        </div>
    );
}
export default MainTable;