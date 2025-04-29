import { useEffect, useState } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

const ChartsView = ({ data, dataKey }) => {
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        if (data.length > 0) {
            setLoading(false);
        }
    }, [data]);

    if (loading) {
        return <p>Učitavanje...</p>;
    }

    if (data.length === 0) {
        return <p>NEMA PODATAKA ZA IZABRANI MJESEC!</p>;
    }

    return (
        <ResponsiveContainer width="100%" height="100%">
            <BarChart
                data={data}
                margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
            >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis
                    dataKey="vehicle"
                    angle={-45}
                    textAnchor="end"
                    interval={0}
                    height={100} // Povećajte visinu za bolji prikaz
                />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey={dataKey} fill="#8884d8" />
            </BarChart>
        </ResponsiveContainer>
    );
};

export default ChartsView;