import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const GuestDashboard = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.text}>Welcome, Guest!</Text>
      <Text style={styles.subtext}>This is your guest dashboard.</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f2f2f2',
  },
  text: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  subtext: {
    marginTop: 10,
    fontSize: 14,
    color: '#666',
  },
});

export default GuestDashboard;